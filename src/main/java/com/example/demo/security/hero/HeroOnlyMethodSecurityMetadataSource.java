package com.example.demo.security.hero;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.method.AbstractMethodSecurityMetadataSource;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;

public class HeroOnlyMethodSecurityMetadataSource extends AbstractMethodSecurityMetadataSource {
  @Override
  public Collection<ConfigAttribute> getAttributes(Method method, Class<?> targetClass) {
    if (method.getDeclaringClass() == Object.class) {
      return Collections.emptyList();
    }

    HeroOnly annotation = method.getAnnotation(HeroOnly.class);

    if(annotation == null) {
      return Collections.emptyList();
    }

    HeroOnlySecurity heroOnlySecurity = new HeroOnlySecurity(annotation.value());
    return Collections.singletonList(heroOnlySecurity);
  }

  @Override
  public Collection<ConfigAttribute> getAllConfigAttributes() {
    return null;
  }


}
