package com.github.yingzhuo.playground.controller.data;

import spring.turbo.databinding.AbstractPropertyEditor;
import spring.turbo.exception.DataBindingException;
import spring.turbo.util.NumberParseUtils;

@SuppressWarnings("unused")
public class LocationEditor extends AbstractPropertyEditor<Location> {

    @Override
    protected Location convert(String text) throws DataBindingException {

        var parts = text.split("@@");
        if (parts.length != 2) {
            throw DataBindingException.of("地理位置信息格式错误");
        }

        try {
            var location = new Location();
            location.setLat(NumberParseUtils.parse(parts[0], Double.class));
            location.setLng(NumberParseUtils.parse(parts[1], Double.class));
            return location;
        } catch (Exception e) {
            throw DataBindingException.of("地理位置信息格式错误");
        }
    }

}
