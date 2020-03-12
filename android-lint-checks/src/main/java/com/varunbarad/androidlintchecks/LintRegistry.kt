package com.varunbarad.androidlintchecks

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue
import com.varunbarad.androidlintchecks.ConstraintLayoutChildNotVisibleDetector.Companion.ISSUE_POSSIBLE_INVISIBLE_VIEW
import com.varunbarad.androidlintchecks.InputTypeDetector.Companion.ISSUE_MISSING_INPUT_TYPE
import com.varunbarad.androidlintchecks.LogWtfDetector.Companion.ISSUE_LOG_WTF
import com.varunbarad.androidlintchecks.WebViewMetricsMetaTagDetector.Companion.ISSUE_MISSING_WEBVIEW_METRICS_META_TAG

class LintRegistry : IssueRegistry() {
    override val api: Int
        get() = CURRENT_API

    override val issues: List<Issue>
        get() = listOf(
            ISSUE_MISSING_INPUT_TYPE,
            ISSUE_MISSING_WEBVIEW_METRICS_META_TAG,
            ISSUE_POSSIBLE_INVISIBLE_VIEW,
            ISSUE_LOG_WTF
        )
}
