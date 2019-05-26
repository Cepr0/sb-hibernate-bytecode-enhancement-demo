package io.github.cepr0.demo.model;

import lombok.Value;

import java.io.Serializable;

@Value
public class SomeData implements Serializable {
	private Integer num;
	private String text;
}
