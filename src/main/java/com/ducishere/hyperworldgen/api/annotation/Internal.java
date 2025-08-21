package com.ducishere.hyperworldgen.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Marks methods, classes, and fields that are internal to HyperWorldGen
 * and should not be used by external devs.
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
public @interface Internal {}
