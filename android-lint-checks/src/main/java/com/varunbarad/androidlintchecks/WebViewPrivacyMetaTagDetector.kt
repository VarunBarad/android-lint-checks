package com.varunbarad.androidlintchecks

import com.android.SdkConstants
import com.android.tools.lint.checks.ManifestDetector
import com.android.tools.lint.detector.api.*
import org.w3c.dom.Element

class WebViewMetricsMetaTagDetector : ManifestDetector() {
    companion object {
        @JvmStatic
        internal val ISSUE_MISSING_WEBVIEW_METRICS_META_TAG = Issue.create(
            id = "MissingWebViewMetricsMetaTag",
            briefDescription = "Specify whether you want to opt-out of metrics tracking via WebView",
            explanation = "Specify whether you want to opt-out of metrics tracking via WebView",
            category = Category.USABILITY,
            priority = 8,
            severity = Severity.ERROR,
            implementation = Implementation(
                WebViewMetricsMetaTagDetector::class.java,
                Scope.MANIFEST_SCOPE
            )
        )
    }

    override fun getApplicableElements(): Collection<String>? {
        return listOf(
            SdkConstants.TAG_APPLICATION
        )
    }

    override fun visitElement(context: XmlContext, element: Element) {
        val containsWebViewMetricsOptOutTag = element.childNodes
            .toList()
            .filter { it.nodeName == "meta-data" }
            .any { node ->
                node.attributes.toPairedNameValueList().filter { attribute ->
                    attribute.first == "android:name" && attribute.second == "android.webkit.WebView.MetricsOptOut"
                }.isNotEmpty()
            }

        if (!containsWebViewMetricsOptOutTag) {
            context.report(
                issue = ISSUE_MISSING_WEBVIEW_METRICS_META_TAG,
                location = context.getLocation(element),
                message = ISSUE_MISSING_WEBVIEW_METRICS_META_TAG.getExplanation(TextFormat.TEXT)
            )
        }
    }
}
