package com.example.demo.security.hero;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

public class HeroOnlyVoter implements AccessDecisionVoter<Object> {
  @Override
  public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
    if (authentication == null) {
      return ACCESS_DENIED;
    }

    for (ConfigAttribute attribute : attributes) {
      if (supports(attribute)) {
        return authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_SUPER_HERO"))
            ? ACCESS_GRANTED
            : ACCESS_DENIED;
      }
    }

    return ACCESS_ABSTAIN;
  }

  @Override
  public boolean supports(ConfigAttribute attribute) {
    return attribute instanceof HeroOnlySecurity;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return true;
  }
}
