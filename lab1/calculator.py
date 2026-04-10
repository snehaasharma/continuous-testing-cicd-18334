"""Minimal sample module for Day 1 CI lab (unit tests only in pipeline)."""


def add(a: float, b: float) -> float:
    return a + b


def divide(a: float, b: float) -> float:
    if b == 0:
        raise ValueError("division by zero")
    return a / b
