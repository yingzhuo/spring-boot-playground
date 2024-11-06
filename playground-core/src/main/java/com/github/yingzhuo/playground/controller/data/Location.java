package com.github.yingzhuo.playground.controller.data;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Location implements Serializable {

    private Double lat;
    private Double lng;

}
