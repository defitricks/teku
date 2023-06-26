/*
 * Copyright ConsenSys Software Inc., 2022
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package tech.pegasys.teku.spec.config;

public interface NetworkingSpecConfig {

  int getGossipMaxSize();

  int getMaxChunkSize();

  int getEpochsPerSubnetSubscription();

  int getMinEpochsForBlockRequests();

  int getSubnetsPerNode();

  int getAttestationSubnetCount();

  // The number of extra bits of a NodeId to use when mapping to a subscribed subnet
  int getAttestationSubnetExtraBits();

  // int(ceillog2(ATTESTATION_SUBNET_COUNT) + ATTESTATION_SUBNET_EXTRA_BITS)
  int getAttestationSubnetPrefixBits();

  default NetworkingSpecConfig getNetworkingConfig() {
    return this;
  }
}
