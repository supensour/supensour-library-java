package com.supensour.library.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Suprayan Yapura
 * @since March 29, 2020
 */
@Data
@NoArgsConstructor(staticName = "empty")
@AllArgsConstructor(staticName = "of")
public class Tetrad<T, R, U, V> {

  T first;

  R second;

  U third;

  V fourth;

}
