# This file was auto-generated by Fern from our API Definition.

from seed import Language
from seed.client import AsyncSeedTrace, SeedTrace

from .utilities import validate_response


async def test_set_num_warm_instances(client: SeedTrace, async_client: AsyncSeedTrace) -> None:
    # Type ignore to avoid mypy complaining about the function not being meant to return a value
    assert client.sysprop.set_num_warm_instances(language=Language.JAVA, num_warm_instances=1) is None  # type: ignore[func-returns-value]

    assert await async_client.sysprop.set_num_warm_instances(language=Language.JAVA, num_warm_instances=1) is None  # type: ignore[func-returns-value]


async def test_get_num_warm_instances(client: SeedTrace, async_client: AsyncSeedTrace) -> None:
    expected_response = {"string": 1}
    expected_types = ("dict", {0: (None, "integer")})
    response = client.sysprop.get_num_warm_instances()
    validate_response(response, expected_response, expected_types)

    async_response = await async_client.sysprop.get_num_warm_instances()
    validate_response(async_response, expected_response, expected_types)
