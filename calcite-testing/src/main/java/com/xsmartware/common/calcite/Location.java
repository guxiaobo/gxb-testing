package com.xsmartware.common.calcite;

import java.util.Objects;

/**
 * Location model.
 */
public class  Location {
  public final int x;
  public final int y;

  public Location(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override public String toString() {
    return "Location [x: " + x + ", y: " + y + "]";
  }

  @Override public boolean equals(Object obj) {
    return obj == this
        || obj instanceof Location
        && x == ((Location) obj).x
        && y == ((Location) obj).y;
  }

  @Override public int hashCode() {
    return Objects.hash(x, y);
  }
}
