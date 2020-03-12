package com.varunbarad.androidlintchecks

import com.android.tools.lint.detector.api.*
import com.intellij.psi.PsiMethod
import org.jetbrains.uast.UCallExpression

class LogWtfDetector : Detector(), SourceCodeScanner {
    companion object {
        @JvmStatic
        internal val ISSUE_LOG_WTF = Issue.create(
            id = "LogWtfPresent",
            briefDescription = "VarunVarun",
            explanation = "VarunVarunVarun",
            category = Category.PERFORMANCE,
            priority = 8,
            severity = Severity.ERROR,
            implementation = Implementation(
                LogWtfDetector::class.java,
                Scope.JAVA_FILE_SCOPE
            )
        )
    }

    override fun getApplicableMethodNames() = listOf("wtf")

    override fun visitMethodCall(
        context: JavaContext,
        node: UCallExpression,
        method: PsiMethod
    ) {
        assert(node.methodName == "wtf")

        if (context.evaluator.isMemberInClass(method, "android.util.Log")) {
            val quickFix = LintFix.create()
                .name("Use Log.e()")
                .replace()
                .text(method.name)
                .with("e")
                .robot(true)
                .independent(true)
                .build()

            context.report(
                issue = ISSUE_LOG_WTF,
                scope = node,
                location = context.getCallLocation(
                    call = node,
                    includeReceiver = false,
                    includeArguments = false
                ),
                message = ISSUE_LOG_WTF.getBriefDescription(TextFormat.TEXT),
                quickfixData = quickFix
            )
        }
    }
}

