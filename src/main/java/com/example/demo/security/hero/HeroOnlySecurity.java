package com.example.demo.security.hero;

import org.springframework.security.access.ConfigAttribute;

public class HeroOnlySecurity implements ConfigAttribute {
  private final boolean hero;

  public HeroOnlySecurity(boolean hero) {
    this.hero = hero;
  }

  public boolean isHero() {
    return hero;
  }

  @Override
  public String getAttribute() {
    return null;
  }
}
