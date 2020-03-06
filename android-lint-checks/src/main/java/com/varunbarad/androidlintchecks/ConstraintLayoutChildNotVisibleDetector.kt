package com.varunbarad.androidlintchecks

import com.android.tools.lint.detector.api.*
import org.w3c.dom.Element
import org.w3c.dom.Node

class ConstraintLayoutChildNotVisibleDetector : LayoutDetector() {
    companion object {
        @JvmStatic
        internal val ISSUE_POSSIBLE_INVISIBLE_VIEW = Issue.create(
            id = "PossibleInvisibleView",
            briefDescription = "The constraints on this view might make it invisible.",
            explanation = "This view will probably become invisible. Check all the constraints related to parent.",
            category = Category.USABILITY,
            priority = 8,
            severity = Severity.WARNING,
            implementation = Implementation(
                ConstraintLayoutChildNotVisibleDetector::class.java,
                Scope.ALL_RESOURCES_SCOPE
            )
        )

        @JvmStatic
        private val PARENT_CONSTRAINT_ATTRIBUTE_NAMES = listOf(
            "app:layout_constraintLeft_toRightOf",
            "app:layout_constraintRight_toLeftOf",
            "app:layout_constraintStart_toEndOf",
            "app:layout_constraintEnd_toStartOf",
            "app:layout_constraintTop_toBottomOf",
            "app:layout_constraintBottom_toTopOf"
        )
    }

    override fun getApplicableElements(): Collection<String>? {
        return listOf(
            "androidx.constraintlayout.widget.ConstraintLayout",
            "android.support.constraint.ConstraintLayout"
        )
    }

    override fun visitElement(context: XmlContext, element: Element) {
        val childNodes: List<Node>? = element.childNodes?.toList()

        if (childNodes != null) {
            for (childView in childNodes) {
                val attributes: List<Pair<String, String>>? =
                    childView.attributes?.toPairedNameValueList()

                attributes?.filter { attribute ->
                    PARENT_CONSTRAINT_ATTRIBUTE_NAMES.contains(attribute.first)
                }?.forEach { attribute ->
                    if (attribute.second == "parent") {
                        context.report(
                            issue = ISSUE_POSSIBLE_INVISIBLE_VIEW,
                            location = context.getLocation(childView),
                            message = ISSUE_POSSIBLE_INVISIBLE_VIEW.getExplanation(TextFormat.TEXT)
                        )
                    }
                }
            }
        }
    }
}
