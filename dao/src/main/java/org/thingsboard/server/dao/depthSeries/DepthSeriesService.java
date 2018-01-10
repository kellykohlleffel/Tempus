/**
 * Copyright © 2016-2017 The Thingsboard Authors
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
package org.thingsboard.server.dao.depthSeries;

import com.google.common.util.concurrent.ListenableFuture;
import org.thingsboard.server.common.data.id.EntityId;
import org.thingsboard.server.common.data.kv.DsKvEntry;
import org.thingsboard.server.common.data.kv.DsKvQuery;
import org.thingsboard.server.common.data.kv.TsKvEntry;
import org.thingsboard.server.common.data.kv.TsKvQuery;

import java.util.Collection;
import java.util.List;

/**
 * @author Himanshu Mishra
 */
public interface DepthSeriesService {

    ListenableFuture<List<DsKvEntry>> findAll(EntityId entityId, List<DsKvQuery> queries);

    ListenableFuture<List<DsKvEntry>> findLatest(EntityId entityId, Collection<String> keys);

    ListenableFuture<List<DsKvEntry>> findAllLatest(EntityId entityId);

    ListenableFuture<List<Void>> save(EntityId entityId, DsKvEntry tsKvEntry);

    ListenableFuture<List<Void>> save(EntityId entityId, List<DsKvEntry> dsKvEntry, long ttl);
}
