package com.training.ctcicd.lab2.tests;

import com.training.ctcicd.lab2.config.FrameworkConfig;
import com.training.ctcicd.lab2.pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Day 3 lab — feature-branch exercise: exercises {@link FrameworkConfig} and {@link LoginPage}
 * without trivial assertions.
 */
@Tag("smoke")
class FeatureBranchExerciseTest {

    @Test
    @DisplayName("Login URL is derived from configured base URL")
    void login_page_url_uses_configured_base() {
        var cfg = new FrameworkConfig().loadDefaults();
        var page = new LoginPage(cfg.baseUrl(), cfg.testUsers());

        assertThat(page.url()).startsWith(cfg.baseUrl()).endsWith("/login");
    }

    @Test
    @DisplayName("Known user from config.properties can authenticate")
    void successful_login_for_configured_user() {
        var cfg = new FrameworkConfig().loadDefaults();
        var page = new LoginPage(cfg.baseUrl(), cfg.testUsers());

        var result = page.submitCredentials("alice", "alice123");

        assertThat(result.success()).isTrue();
        assertThat(result.message()).isEqualTo("ok");
    }

    @Test
    @DisplayName("Wrong password is rejected with a clear failure")
    void invalid_password_is_rejected() {
        var cfg = new FrameworkConfig().loadDefaults();
        var page = new LoginPage(cfg.baseUrl(), cfg.testUsers());

        var result = page.submitCredentials("alice", "wrong-password");

        assertThat(result.success()).isFalse();
        assertThat(result.message()).containsIgnoringCase("invalid");
    }
}
