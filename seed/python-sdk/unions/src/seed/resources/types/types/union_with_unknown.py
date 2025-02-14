# This file was auto-generated by Fern from our API Definition.

from __future__ import annotations

import typing

from .foo import Foo

try:
    import pydantic.v1 as pydantic  # type: ignore
except ImportError:
    import pydantic  # type: ignore


class UnionWithUnknown_Foo(Foo):
    type: typing.Literal["foo"]

    class Config:
        frozen = True
        smart_union = True
        allow_population_by_field_name = True
        populate_by_name = True


class UnionWithUnknown_Unknown(pydantic.BaseModel):
    type: typing.Literal["unknown"]

    class Config:
        frozen = True
        smart_union = True


UnionWithUnknown = typing.Union[UnionWithUnknown_Foo, UnionWithUnknown_Unknown]
