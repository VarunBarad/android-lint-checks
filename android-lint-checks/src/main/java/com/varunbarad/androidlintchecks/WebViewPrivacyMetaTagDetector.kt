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
            briefDescription = "Specify whether you want to opt-out of metrics tracking via WebView.",
            explanation = "WebView can upload diagnostic data to Google. You can opt-out by specifying a `meta-data` tag under your `application` tag in manifest.",
            category = Category.SECURITY,
            priority = 8,
            severity = Severity.ERROR,
            implementation = Implementation(
                WebViewMetricsMetaTagDetector::class.java,
                Scope.MANIFEST_SCOPE
            )
        ).addMoreInfo("https://developer.android.com/guide/webapps/managing-webview#metrics")
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
