# This file was auto-generated by Fern from our API Definition.

from __future__ import annotations

import datetime as dt
import typing

try:
    import pydantic.v1 as pydantic  # type: ignore
except ImportError:
    import pydantic  # type: ignore


class UnionWithOptionalTime_Date(pydantic.BaseModel):
    type: typing.Literal["date"]
    value: typing.Optional[dt.date]

    class Config:
        frozen = True
        smart_union = True


class UnionWithOptionalTime_Dateimte(pydantic.BaseModel):
    type: typing.Literal["dateimte"]
    value: typing.Optional[dt.datetime]

    class Config:
        frozen = True
        smart_union = True


UnionWithOptionalTime = typing.Union[UnionWithOptionalTime_Date, UnionWithOptionalTime_Dateimte]
