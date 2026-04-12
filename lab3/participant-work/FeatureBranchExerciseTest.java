package com.training.ctcicd.lab2.tests;

import com.training.ctcicd.lab2.config.FrameworkConfig;
import com.training.ctcicd.lab2.pages.LoginPage;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Day 3 lab — copy this file into:
 * lab2/maven-junit5/src/test/java/com/training/ctcicd/lab2/tests/
 * Then improve the test body before opening your PR (reviewers should reject trivial assertions).
 */
@Tag("smoke")
class FeatureBranchExerciseTest {

    @Test
    void login_page_url_uses_configured_base() {
        var cfg = new FrameworkConfig().loadDefaults();
        var page = new LoginPage(cfg.baseUrl(), cfg.testUsers());

        // Lab: add at least one meaningful assertion (e.g. URL shape, successful login for a known user).
        assertThat(page.url()).contains("/login");
    }
}
