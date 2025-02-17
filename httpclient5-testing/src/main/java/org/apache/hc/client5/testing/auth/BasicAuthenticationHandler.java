/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.hc.client5.testing.auth;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.apache.hc.client5.http.auth.StandardAuthScheme;
import org.apache.hc.client5.http.utils.Base64;
import org.apache.hc.core5.util.Args;

public class BasicAuthenticationHandler extends AbstractAuthenticationHandler {

    private final Charset charset;

    /**
     * @since 5.3
     */
    public BasicAuthenticationHandler(final Charset charset) {
        this.charset = Args.notNull(charset, "Charset");
    }

    /**
     * @deprecated Use {@link #BasicAuthenticationHandler(Charset)}
     */
    @Deprecated
    public BasicAuthenticationHandler() {
        this(StandardCharsets.US_ASCII);
    }

    @Override
    String getSchemeName() {
        return StandardAuthScheme.BASIC;
    }

    @Override
    String decodeChallenge(final String challenge) throws IllegalArgumentException {
        final byte[] bytes = challenge.getBytes(StandardCharsets.US_ASCII);
        final Base64 codec = new Base64();
        return new String(codec.decode(bytes), charset);
    }

}
