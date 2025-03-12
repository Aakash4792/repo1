/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License
 * 2.0; you may not use this file except in compliance with the Elastic License
 * 2.0.
 */

package org.elasticsearch.xpack.fleet.action;

import org.elasticsearch.action.ActionResponseValidationException;
import org.elasticsearch.common.io.stream.Writeable;
import org.elasticsearch.test.AbstractWireSerializingTestCase;

import static org.junit.Assert.assertArrayEquals;

public class GetSecretResponseTests extends AbstractWireSerializingTestCase<GetSecretResponse> {

    @Override
    protected Writeable.Reader<GetSecretResponse> instanceReader() {
        return GetSecretResponse::new;
    }

    @Override
    protected GetSecretResponse createTestInstance() {
        return new GetSecretResponse(randomAlphaOfLength(10), randomAlphaOfLength(10));
    }

    @Override
    protected GetSecretResponse mutateInstance(GetSecretResponse instance) {
        return new GetSecretResponse(instance.id(), randomAlphaOfLength(10));
    }

    public void testValidateResponseWithMultiValue() {
        String[] secrets = { "secret1", "secret2" };
        GetSecretResponse res = new GetSecretResponse(randomAlphaOfLength(10), secrets);
        ActionResponseValidationException e = res.validate();
        assertNull(e);
        assertArrayEquals(secrets, (String[]) res.value());
    }
}
