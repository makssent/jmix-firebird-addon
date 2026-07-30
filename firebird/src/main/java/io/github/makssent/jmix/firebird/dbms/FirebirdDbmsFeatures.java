/*
 * Copyright 2026 Haulmont.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.makssent.jmix.firebird.dbms;

import io.jmix.data.persistence.DbmsFeatures;
import org.springframework.stereotype.Component;

import org.jspecify.annotations.Nullable;
import java.util.HashMap;
import java.util.Map;

@Component("firebirdDbmsFeatures")
public class FirebirdDbmsFeatures implements DbmsFeatures {

    @Override
    public Map<String, String> getJpaParameters() {
        HashMap<String, String> params = new HashMap<>();
        params.put("eclipselink.target-database", "io.github.makssent.jmix.firebird.dbms.JmixFirebirdPlatform");
        return params;
    }

    @Override
    public String getTimeStampType() {
        return "timestamp";
    }

    @Nullable
    @Override
    public String getUuidTypeClassName() {
        return null;
    }

    @Nullable
    @Override
    public String getTransactionTimeoutStatement() {
        return null;
    }

    @Override
    public String getUniqueConstraintViolationPattern() {
        return "(?:violation of PRIMARY or UNIQUE KEY constraint"
                + "|attempt to store duplicate value \\(visible to active transactions\\) in unique index)"
                + " \"(.+?)\"";
    }

    @Override
    public boolean isNullsLastSorting() {
        return false;
    }

    @Override
    public boolean supportsLobSortingAndFiltering() {
        return false;
    }

    @Override
    public String getTypeAndVersion() {
        return "firebird";
    }
}