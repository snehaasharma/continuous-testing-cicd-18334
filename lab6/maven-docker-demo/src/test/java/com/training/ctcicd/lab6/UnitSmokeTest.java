package com.training.ctcicd.lab6;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/** Fast tests that do not need Docker — always run in IDE and CI. */
class UnitSmokeTest {

    @Test
    void build_pipeline_placeholder_is_green() {
        assertThat(Placeholder.class.getSimpleName()).isEqualTo("Placeholder");
    }

    @Test
    void uuid_style_prefixes_are_unique() {
        String a = "user_test_" + java.util.UUID.randomUUID();
        String b = "user_test_" + java.util.UUID.randomUUID();
        assertThat(a).isNotEqualTo(b);
    }
}
