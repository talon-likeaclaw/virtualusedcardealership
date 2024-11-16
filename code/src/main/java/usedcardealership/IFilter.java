package usedcardealership;

import java.util.*;

public interface IFilter<T, Param> {
  List<T> filter(List<T> items, Param param);
}