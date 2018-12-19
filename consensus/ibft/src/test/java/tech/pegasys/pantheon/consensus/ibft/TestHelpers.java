/*
 * Copyright 2018 ConsenSys AG.
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
package tech.pegasys.pantheon.consensus.ibft;

import tech.pegasys.pantheon.ethereum.core.Address;
import tech.pegasys.pantheon.ethereum.core.Block;
import tech.pegasys.pantheon.ethereum.core.BlockDataGenerator;
import tech.pegasys.pantheon.ethereum.core.BlockDataGenerator.BlockOptions;
import tech.pegasys.pantheon.util.bytes.BytesValue;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TestHelpers {

  public static ConsensusRoundIdentifier createFrom(
      final ConsensusRoundIdentifier initial, final int offSetSequence, final int offSetRound) {
    return new ConsensusRoundIdentifier(
        initial.getSequenceNumber() + offSetSequence, initial.getRoundNumber() + offSetRound);
  }

  public static Block createProposalBlock(final List<Address> validators, final int round) {
    final BytesValue extraData =
        new IbftExtraData(
                BytesValue.wrap(new byte[32]),
                Collections.emptyList(),
                Optional.empty(),
                round,
                validators)
            .encode();
    final BlockOptions blockOptions =
        BlockOptions.create()
            .setExtraData(extraData)
            .setBlockHashFunction(IbftBlockHashing::calculateDataHashForCommittedSeal);
    return new BlockDataGenerator().block(blockOptions);
  }
}
