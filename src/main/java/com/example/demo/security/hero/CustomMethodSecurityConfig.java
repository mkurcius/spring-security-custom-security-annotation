package com.example.demo.security.hero;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.method.MethodSecurityMetadataSource;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@EnableGlobalMethodSecurity(securedEnabled = true)
public class CustomMethodSecurityConfig extends GlobalMethodSecurityConfiguration {
  @Override
  protected MethodSecurityMetadataSource customMethodSecurityMetadataSource() {
    return new HeroOnlyMethodSecurityMetadataSource();
  }

  @Override
  protected AccessDecisionManager accessDecisionManager() {
    List<AccessDecisionVoter<?>> decisionVoters = new ArrayList<>(defaultVoters());
    decisionVoters.add(new HeroOnlyVoter());

    return new AffirmativeBased(decisionVoters);
  }

  private List<AccessDecisionVoter<?>> defaultVoters() {
    AccessDecisionManager accessDecisionManager = super.accessDecisionManager();

    if (accessDecisionManager instanceof AbstractAccessDecisionManager) {
      return ((AbstractAccessDecisionManager) accessDecisionManager).getDecisionVoters();
    }

    return Collections.emptyList();
  }
}
