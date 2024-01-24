/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2024 Aliaksei Bialiauski
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.github.h1alexbel.ghquota;

import com.jcabi.github.Limit;
import com.jcabi.github.Limits;
import com.jcabi.github.RtGithub;
import java.io.IOException;
import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * GitHub API Quota.
 *
 * @since 0.0.0
 */
public final class QuotaIsFine implements ExecutionCondition {

    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(
        final ExtensionContext ect
    ) {
        ConditionEvaluationResult ret;
        try {
            if (QuotaIsFine.exceeded()) {
                ret = ConditionEvaluationResult.disabled("Quota exceeded");
            } else {
                ret = ConditionEvaluationResult.enabled("Quota is fine");
            }
        } catch (final IOException ex) {
            ret = ConditionEvaluationResult.disabled(
                String.format("Failed to check online status: %s", ex.getMessage())
            );
        }
        return ret;
    }

    /**
     * Checks exceeded or not.
     * @return Exceeded or not
     * @throws IOException if I/O fails
     */
    private static boolean exceeded() throws IOException {
        final Limit.Smart limit = new Limit.Smart(
            new RtGithub().limits().get(Limits.CORE)
        );
        return limit.remaining() < 1;
    }
}
