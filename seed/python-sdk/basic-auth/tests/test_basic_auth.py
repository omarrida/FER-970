# This file was auto-generated by Fern from our API Definition.

from seed.client import AsyncSeedBasicAuth, SeedBasicAuth

from .utilities import validate_response


async def test_get_with_basic_auth(client: SeedBasicAuth, async_client: AsyncSeedBasicAuth) -> None:
    expected_response = True
    expected_types = None
    response = client.basic_auth.get_with_basic_auth()
    validate_response(response, expected_response, expected_types)

    async_response = await async_client.basic_auth.get_with_basic_auth()
    validate_response(async_response, expected_response, expected_types)


async def test_post_with_basic_auth(client: SeedBasicAuth, async_client: AsyncSeedBasicAuth) -> None:
    expected_response = True
    expected_types = None
    response = client.basic_auth.post_with_basic_auth(request={"key": "value"})
    validate_response(response, expected_response, expected_types)

    async_response = await async_client.basic_auth.post_with_basic_auth(request={"key": "value"})
    validate_response(async_response, expected_response, expected_types)
