/*
 * Copyright Consensys Software Inc., 2022
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

package tech.pegasys.teku.api.schema.eip7732;

import static tech.pegasys.teku.api.schema.SchemaConstants.DESCRIPTION_BYTES32;
import static tech.pegasys.teku.api.schema.SchemaConstants.EXAMPLE_UINT64;
import static tech.pegasys.teku.api.schema.SchemaConstants.PATTERN_BYTES32;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import org.apache.tuweni.bytes.Bytes32;
import tech.pegasys.teku.api.schema.BeaconBlockHeader;
import tech.pegasys.teku.api.schema.Checkpoint;
import tech.pegasys.teku.api.schema.Eth1Data;
import tech.pegasys.teku.api.schema.Fork;
import tech.pegasys.teku.api.schema.Validator;
import tech.pegasys.teku.api.schema.altair.BeaconStateAltair;
import tech.pegasys.teku.api.schema.altair.SyncCommittee;
import tech.pegasys.teku.api.schema.capella.HistoricalSummary;
import tech.pegasys.teku.api.schema.electra.PendingBalanceDeposit;
import tech.pegasys.teku.api.schema.electra.PendingConsolidation;
import tech.pegasys.teku.api.schema.electra.PendingPartialWithdrawal;
import tech.pegasys.teku.infrastructure.ssz.collections.SszBitvector;
import tech.pegasys.teku.infrastructure.ssz.schema.SszListSchema;
import tech.pegasys.teku.infrastructure.unsigned.UInt64;
import tech.pegasys.teku.spec.SpecVersion;
import tech.pegasys.teku.spec.datastructures.execution.versions.eip7732.ExecutionPayloadHeaderSchemaEip7732;
import tech.pegasys.teku.spec.datastructures.state.SyncCommittee.SyncCommitteeSchema;
import tech.pegasys.teku.spec.datastructures.state.beaconstate.BeaconState;
import tech.pegasys.teku.spec.datastructures.state.beaconstate.MutableBeaconState;
import tech.pegasys.teku.spec.datastructures.state.beaconstate.versions.eip7732.BeaconStateSchemaEip7732;
import tech.pegasys.teku.spec.datastructures.state.beaconstate.versions.eip7732.MutableBeaconStateEip7732;

public class BeaconStateEip7732 extends BeaconStateAltair {

  @JsonProperty("latest_execution_payload_header")
  public final ExecutionPayloadHeaderEip7732 latestExecutionPayloadHeader;

  @JsonProperty("next_withdrawal_index")
  @Schema(type = "string", example = EXAMPLE_UINT64)
  public final UInt64 nextWithdrawalIndex;

  @JsonProperty("next_withdrawal_validator_index")
  @Schema(type = "string", example = EXAMPLE_UINT64)
  public final UInt64 nextWithdrawalValidatorIndex;

  @JsonProperty("historical_summaries")
  public final List<HistoricalSummary> historicalSummaries;

  @JsonProperty("deposit_requests_start_index")
  public final UInt64 depositRequestsStartIndex;

  @JsonProperty("deposit_balance_to_consume")
  public final UInt64 depositBalanceToConsume;

  @JsonProperty("exit_balance_to_consume")
  public final UInt64 exitBalanceToConsume;

  @JsonProperty("earliest_exit_epoch")
  public final UInt64 earliestExitEpoch;

  @JsonProperty("consolidation_balance_to_consume")
  public final UInt64 consolidationBalanceToConsume;

  @JsonProperty("earliest_consolidation_epoch")
  public final UInt64 earliestConsolidationEpoch;

  @JsonProperty("pending_balance_deposits")
  public final List<PendingBalanceDeposit> pendingBalanceDeposits;

  @JsonProperty("pending_partial_withdrawals")
  public final List<PendingPartialWithdrawal> pendingPartialWithdrawals;

  @JsonProperty("pending_consolidations")
  public final List<PendingConsolidation> pendingConsolidations;

  @JsonProperty("latest_block_hash")
  @Schema(
      type = "string",
      format = "byte",
      pattern = PATTERN_BYTES32,
      description = DESCRIPTION_BYTES32)
  public final Bytes32 latestBlockHash;

  @JsonProperty("latest_full_slot")
  @Schema(type = "string", format = "uint64")
  public final UInt64 latestFullSlot;

  @JsonProperty("latest_withdrawals_root")
  @Schema(
      type = "string",
      format = "byte",
      pattern = PATTERN_BYTES32,
      description = DESCRIPTION_BYTES32)
  public final Bytes32 latestWithdrawalsRoot;

  public BeaconStateEip7732(
      @JsonProperty("genesis_time") final UInt64 genesisTime,
      @JsonProperty("genesis_validators_root") final Bytes32 genesisValidatorsRoot,
      @JsonProperty("slot") final UInt64 slot,
      @JsonProperty("fork") final Fork fork,
      @JsonProperty("latest_block_header") final BeaconBlockHeader latestBlockHeader,
      @JsonProperty("block_roots") final List<Bytes32> blockRoots,
      @JsonProperty("state_roots") final List<Bytes32> stateRoots,
      @JsonProperty("historical_roots") final List<Bytes32> historicalRoots,
      @JsonProperty("eth1_data") final Eth1Data eth1Data,
      @JsonProperty("eth1_data_votes") final List<Eth1Data> eth1DataVotes,
      @JsonProperty("eth1_deposit_index") final UInt64 eth1DepositIndex,
      @JsonProperty("validators") final List<Validator> validators,
      @JsonProperty("balances") final List<UInt64> balances,
      @JsonProperty("randao_mixes") final List<Bytes32> randaoMixes,
      @JsonProperty("slashings") final List<UInt64> slashings,
      @JsonProperty("previous_epoch_participation") final byte[] previousEpochParticipation,
      @JsonProperty("current_epoch_participation") final byte[] currentEpochParticipation,
      @JsonProperty("justification_bits") final SszBitvector justificationBits,
      @JsonProperty("previous_justified_checkpoint") final Checkpoint previousJustifiedCheckpoint,
      @JsonProperty("current_justified_checkpoint") final Checkpoint currentJustifiedCheckpoint,
      @JsonProperty("finalized_checkpoint") final Checkpoint finalizedCheckpoint,
      @JsonProperty("inactivity_scores") final List<UInt64> inactivityScores,
      @JsonProperty("current_sync_committee") final SyncCommittee currentSyncCommittee,
      @JsonProperty("next_sync_committee") final SyncCommittee nextSyncCommittee,
      @JsonProperty("latest_execution_payload_header")
          final ExecutionPayloadHeaderEip7732 latestExecutionPayloadHeader,
      @JsonProperty("next_withdrawal_index") final UInt64 nextWithdrawalIndex,
      @JsonProperty("next_withdrawal_validator_index") final UInt64 nextWithdrawalValidatorIndex,
      @JsonProperty("historical_summaries") final List<HistoricalSummary> historicalSummaries,
      @JsonProperty("deposit_requests_start_index") final UInt64 depositRequestsStartIndex,
      @JsonProperty("deposit_balance_to_consume") final UInt64 depositBalanceToConsume,
      @JsonProperty("exit_balance_to_consume") final UInt64 exitBalanceToConsume,
      @JsonProperty("earliest_exit_epoch") final UInt64 earliestExitEpoch,
      @JsonProperty("consolidation_balance_to_consume") final UInt64 consolidationBalanceToConsume,
      @JsonProperty("earliest_consolidation_epoch") final UInt64 earliestConsolidationEpoch,
      @JsonProperty("pending_balance_deposits")
          final List<PendingBalanceDeposit> pendingBalanceDeposits,
      @JsonProperty("pending_partial_withdrawals")
          final List<PendingPartialWithdrawal> pendingPartialWithdrawals,
      @JsonProperty("pending_consolidations")
          final List<PendingConsolidation> pendingConsolidations,
      @JsonProperty("latest_block_hash") final Bytes32 latestBlockHash,
      @JsonProperty("latest_full_slot") final UInt64 latestFullSlot,
      @JsonProperty("latest_withdrawals_root") final Bytes32 latestWithdrawalsRoot) {
    super(
        genesisTime,
        genesisValidatorsRoot,
        slot,
        fork,
        latestBlockHeader,
        blockRoots,
        stateRoots,
        historicalRoots,
        eth1Data,
        eth1DataVotes,
        eth1DepositIndex,
        validators,
        balances,
        randaoMixes,
        slashings,
        previousEpochParticipation,
        currentEpochParticipation,
        justificationBits,
        previousJustifiedCheckpoint,
        currentJustifiedCheckpoint,
        finalizedCheckpoint,
        inactivityScores,
        currentSyncCommittee,
        nextSyncCommittee);
    this.latestExecutionPayloadHeader = latestExecutionPayloadHeader;
    this.nextWithdrawalIndex = nextWithdrawalIndex;
    this.nextWithdrawalValidatorIndex = nextWithdrawalValidatorIndex;
    this.historicalSummaries = historicalSummaries;
    this.depositRequestsStartIndex = depositRequestsStartIndex;
    this.depositBalanceToConsume = depositBalanceToConsume;
    this.exitBalanceToConsume = exitBalanceToConsume;
    this.earliestExitEpoch = earliestExitEpoch;
    this.consolidationBalanceToConsume = consolidationBalanceToConsume;
    this.earliestConsolidationEpoch = earliestConsolidationEpoch;
    this.pendingBalanceDeposits = pendingBalanceDeposits;
    this.pendingPartialWithdrawals = pendingPartialWithdrawals;
    this.pendingConsolidations = pendingConsolidations;
    this.latestBlockHash = latestBlockHash;
    this.latestFullSlot = latestFullSlot;
    this.latestWithdrawalsRoot = latestWithdrawalsRoot;
  }

  public BeaconStateEip7732(final BeaconState beaconState) {
    super(beaconState);
    final tech.pegasys.teku.spec.datastructures.state.beaconstate.versions.eip7732
            .BeaconStateEip7732
        eip7732 = beaconState.toVersionEip7732().orElseThrow();
    this.latestExecutionPayloadHeader =
        new ExecutionPayloadHeaderEip7732(
            tech.pegasys.teku.spec.datastructures.execution.versions.eip7732
                .ExecutionPayloadHeaderEip7732.required(eip7732.getLatestExecutionPayloadHeader()));
    this.nextWithdrawalIndex = eip7732.getNextWithdrawalIndex();
    this.nextWithdrawalValidatorIndex = eip7732.getNextWithdrawalValidatorIndex();
    this.historicalSummaries =
        eip7732.getHistoricalSummaries().stream().map(HistoricalSummary::new).toList();
    this.depositRequestsStartIndex = eip7732.getDepositRequestsStartIndex();
    this.depositBalanceToConsume = eip7732.getDepositBalanceToConsume();
    this.exitBalanceToConsume = eip7732.getExitBalanceToConsume();
    this.earliestExitEpoch = eip7732.getEarliestExitEpoch();
    this.consolidationBalanceToConsume = eip7732.getConsolidationBalanceToConsume();
    this.earliestConsolidationEpoch = eip7732.getEarliestConsolidationEpoch();
    this.pendingBalanceDeposits =
        eip7732.getPendingBalanceDeposits().stream().map(PendingBalanceDeposit::new).toList();
    this.pendingPartialWithdrawals =
        eip7732.getPendingPartialWithdrawals().stream().map(PendingPartialWithdrawal::new).toList();
    this.pendingConsolidations =
        eip7732.getPendingConsolidations().stream().map(PendingConsolidation::new).toList();
    this.latestBlockHash = eip7732.getLatestBlockHash();
    this.latestFullSlot = eip7732.getLatestFullSlot();
    this.latestWithdrawalsRoot = eip7732.getLatestWithdrawalsRoot();
  }

  @Override
  protected void applyAdditionalFields(
      final MutableBeaconState state, final SpecVersion specVersion) {
    state
        .toMutableVersionEip7732()
        .ifPresent(
            mutableBeaconStateEip7732 ->
                applyEip7732Fields(
                    specVersion,
                    mutableBeaconStateEip7732,
                    BeaconStateSchemaEip7732.required(
                            mutableBeaconStateEip7732.getBeaconStateSchema())
                        .getCurrentSyncCommitteeSchema(),
                    BeaconStateSchemaEip7732.required(
                            mutableBeaconStateEip7732.getBeaconStateSchema())
                        .getLastExecutionPayloadHeaderSchema(),
                    BeaconStateSchemaEip7732.required(
                            mutableBeaconStateEip7732.getBeaconStateSchema())
                        .getHistoricalSummariesSchema(),
                    BeaconStateSchemaEip7732.required(
                            mutableBeaconStateEip7732.getBeaconStateSchema())
                        .getPendingBalanceDepositsSchema(),
                    BeaconStateSchemaEip7732.required(
                            mutableBeaconStateEip7732.getBeaconStateSchema())
                        .getPendingPartialWithdrawalsSchema(),
                    BeaconStateSchemaEip7732.required(
                            mutableBeaconStateEip7732.getBeaconStateSchema())
                        .getPendingConsolidationsSchema(),
                    this));
  }

  protected static void applyEip7732Fields(
      final SpecVersion specVersion,
      final MutableBeaconStateEip7732 state,
      final SyncCommitteeSchema syncCommitteeSchema,
      final ExecutionPayloadHeaderSchemaEip7732 executionPayloadHeaderSchema,
      final SszListSchema<
              tech.pegasys.teku.spec.datastructures.state.versions.capella.HistoricalSummary, ?>
          historicalSummariesSchema,
      final SszListSchema<
              tech.pegasys.teku.spec.datastructures.state.versions.electra.PendingBalanceDeposit, ?>
          pendingBalanceDepositsSchema,
      final SszListSchema<
              tech.pegasys.teku.spec.datastructures.state.versions.electra.PendingPartialWithdrawal,
              ?>
          pendingPartialWithdrawalsSchema,
      final SszListSchema<
              tech.pegasys.teku.spec.datastructures.state.versions.electra.PendingConsolidation, ?>
          pendingConsolidationsSchema,
      final BeaconStateEip7732 instance) {

    BeaconStateAltair.applyAltairFields(state, syncCommitteeSchema, instance);

    state.setLatestExecutionPayloadHeader(
        instance.latestExecutionPayloadHeader.asInternalExecutionPayloadHeader(
            executionPayloadHeaderSchema));

    state.setNextWithdrawalIndex(instance.nextWithdrawalIndex);
    state.setNextWithdrawalValidatorIndex(instance.nextWithdrawalValidatorIndex);
    state.setHistoricalSummaries(
        historicalSummariesSchema.createFromElements(
            instance.historicalSummaries.stream()
                .map(
                    historicalSummary -> historicalSummary.asInternalHistoricalSummary(specVersion))
                .toList()));
    state.setDepositRequestsStartIndex(instance.depositRequestsStartIndex);
    state.setDepositBalanceToConsume(instance.depositBalanceToConsume);
    state.setExitBalanceToConsume(instance.exitBalanceToConsume);
    state.setEarliestExitEpoch(instance.earliestExitEpoch);
    state.setConsolidationBalanceToConsume(instance.consolidationBalanceToConsume);
    state.setEarliestConsolidationEpoch(instance.earliestConsolidationEpoch);
    state.setPendingBalanceDeposits(
        pendingBalanceDepositsSchema.createFromElements(
            instance.pendingBalanceDeposits.stream()
                .map(
                    pendingBalanceDeposit ->
                        pendingBalanceDeposit.asInternalPendingBalanceDeposit(specVersion))
                .toList()));
    state.setPendingPartialWithdrawals(
        pendingPartialWithdrawalsSchema.createFromElements(
            instance.pendingPartialWithdrawals.stream()
                .map(
                    pendingPartialWithdrawal ->
                        pendingPartialWithdrawal.asInternalPendingPartialWithdrawal(specVersion))
                .toList()));
    state.setPendingConsolidations(
        pendingConsolidationsSchema.createFromElements(
            instance.pendingConsolidations.stream()
                .map(
                    pendingConsolidation ->
                        pendingConsolidation.asInternalPendingConsolidation(specVersion))
                .toList()));
    state.setLatestBlockHash(instance.latestBlockHash);
    state.setLatestFullSlot(instance.latestFullSlot);
    state.setLatestWithdrawalsRoot(instance.latestWithdrawalsRoot);
  }
}
