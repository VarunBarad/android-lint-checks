package com.varunbarad.androidlintchecks

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue

class LintRegistry : IssueRegistry() {
    override val api: Int
        get() = CURRENT_API

    override val issues: List<Issue>
        get() = listOf(
            InputTypeDetector.ISSUE_MISSING_INPUT_TYPE,
            WebViewMetricsMetaTagDetector.ISSUE_MISSING_WEBVIEW_METRICS_META_TAG,
            ConstraintLayoutChildNotVisibleDetector.ISSUE_POSSIBLE_INVISIBLE_VIEW,
            LogWtfDetector.ISSUE_LOG_WTF
        )
}
