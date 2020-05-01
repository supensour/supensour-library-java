package com.supensour.library.model.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Suprayan Yapura
 * @since 1.0.0
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
