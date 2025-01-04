package com.github.yingzhuo.playground.service.impl;

import com.github.yingzhuo.playground.entity.UserPasswordHistory;
import com.github.yingzhuo.playground.include.jdbc.DataSourceNames;
import com.github.yingzhuo.playground.include.jdbc.annotation.DataSourceSwitch;
import com.github.yingzhuo.playground.mapper.UserMapper;
import com.github.yingzhuo.playground.mapper.UserPasswordHistoryMapper;
import com.github.yingzhuo.playground.service.UserService;
import com.github.yingzhuo.playground.util.IDGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import spring.turbo.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    /**
     * 每个用户最多保存的密码更新履历条目数量
     */
    private static final int USER_PWD_HISTORY_MAX = 5;

    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserPasswordHistoryMapper userPasswordHistoryMapper;
    private final IDGenerator idGenerator;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    @DataSourceSwitch(DataSourceNames.MASTER)
    public void resetPassword(String userId, String newPassword) {
        Assert.hasText(userId, () -> "userId is required");
        Assert.hasText(newPassword, "newPassword is required");

        log.debug("userId = {}", userId);
        log.debug("newPassword = {}", newPassword);

        var user = userMapper.selectById(userId);
        if (user == null) {
            return;
        }

        // 更改密码
        var pwd = passwordEncoder.encode(newPassword);
        log.debug("hashed-password (spring-security) = {}", pwd);
        user.setPassword(pwd);
        userMapper.updateById(user);

        // 记录密码更新履历
        var his = new UserPasswordHistory();
        his.setId(idGenerator.generate());
        his.setUserId(userId);
        his.setHashedPassword(DigestUtils.md5Hex(newPassword));
        his.setCreateTime(LocalDateTime.now());
        userPasswordHistoryMapper.insert(his);

        // 删除过多的密码更新履历
        var historyList = userPasswordHistoryMapper.findByUserIdOrderByCreateTimeDesc(userId);
        if (historyList != null && historyList.size() > USER_PWD_HISTORY_MAX) {
            var idSetToDelete =
                    historyList.subList(USER_PWD_HISTORY_MAX, historyList.size())
                            .stream()
                            .map(UserPasswordHistory::getId)
                            .filter(Objects::nonNull)
                            .collect(Collectors.toSet());

            log.debug("待删除密码更新履历ID: {}", idSetToDelete);
            userPasswordHistoryMapper.deleteByIds(idSetToDelete);
        } else {
            log.debug("密码更新履历没有超过上限{} (当前 = {})", USER_PWD_HISTORY_MAX, historyList.size());
        }
    }

}
