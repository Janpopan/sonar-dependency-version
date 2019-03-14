/*
 * Dependency-Version Plugin for SonarQube
 * Copyright (C) 2019 EDEKA DIGITAL GmbH
 * dpp@edeka.de
 */
package org.sonar.dependencyversion.rule;

import org.sonar.api.rule.RuleStatus;
import org.sonar.api.rule.Severity;
import org.sonar.api.server.rule.RulesDefinition;

import javax.annotation.ParametersAreNonnullByDefault;

import static org.sonar.dependencyversion.base.DependencyVersionConstants.*;

public class DependencyVersionRuleDefinition implements RulesDefinition {
  /**
   * This method is executed when server is started.
   *
   * @param context
   */
  @Override
  @ParametersAreNonnullByDefault
  public void define(Context context) {

    NewRepository repo = context.createRepository(REPOSITORY_KEY, LANGUAGE_KEY);
    repo.setName(REPOSITORY_KEY);
    NewRule rule = repo.createRule(RULE_KEY);
    rule.addTags("outdated-dependency");
    rule.setName("Using outdated dependencies");
    rule.setSeverity(Severity.MAJOR);
    rule.setStatus(RuleStatus.READY);

    String description =
        "<p>Components, such as libraries, frameworks, and other software modules, outdated</p>"
            + "<h3>References:</h3>"
            + "<p>This issue was generated by <a href=\"https://github.com/edekadigital/sonar-dependency-version\">Dependency-Version</a>";
    rule.setHtmlDescription(description);

    repo.done();
  }
}
