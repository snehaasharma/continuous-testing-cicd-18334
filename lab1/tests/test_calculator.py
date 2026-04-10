"""Unit tests for calculator — executed in GitHub Actions `unit-tests` job."""

import pytest

from calculator import add, divide


def test_add_integers() -> None:
    assert add(2, 3) == 5


def test_add_floats() -> None:
    assert add(0.1, 0.2) == pytest.approx(0.3)


def test_divide_ok() -> None:
    assert divide(10, 2) == 5


def test_divide_by_zero() -> None:
    with pytest.raises(ValueError, match="division by zero"):
        divide(1, 0)
