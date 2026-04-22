package com.training.ctcicd.lab2.tests;

import com.training.ctcicd.lab2.config.FrameworkConfig;
import com.training.ctcicd.lab2.pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("smoke")
class LoginSmokeLabTest {

    @Test
    @DisplayName("Login URL is built from the configured base URL")
    void login_url_uses_configured_base() {
        var cfg = new FrameworkConfig().loadDefaults();
        var page = new LoginPage(cfg.baseUrl(), cfg.testUsers());

        assertThat(page.url()).startsWith(cfg.baseUrl()).endsWith("/login");
    }

    @Test
    @DisplayName("A configured user can authenticate successfully")
    void known_user_can_authenticate() {
        var cfg = new FrameworkConfig().loadDefaults();
        var page = new LoginPage(cfg.baseUrl(), cfg.testUsers());

        var result = page.submitCredentials("alice", "alice123");

        assertThat(result.success()).isTrue();
        assertThat(result.message()).isEqualTo("ok");
    }

    @Test
    @DisplayName("Wrong password is rejected with a clear failure message")
    void wrong_password_is_rejected() {
        var cfg = new FrameworkConfig().loadDefaults();
        var page = new LoginPage(cfg.baseUrl(), cfg.testUsers());

        var result = page.submitCredentials("alice", "nope");

        assertThat(result.success()).isFalse();
        assertThat(result.message()).containsIgnoringCase("invalid");
    }
}
