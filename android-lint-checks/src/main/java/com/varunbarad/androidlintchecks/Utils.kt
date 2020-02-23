package com.varunbarad.androidlintchecks

import com.android.utils.forEach
import org.w3c.dom.NamedNodeMap
import org.w3c.dom.Node
import org.w3c.dom.NodeList

internal fun NodeList.toList(): List<Node> {
    val outputList = mutableListOf<Node>()

    this.forEach { outputList.add(it) }

    return outputList
}

internal fun NamedNodeMap.toPairedNameValueList(): List<Pair<String, String>> {
    val outputList = mutableListOf<Pair<String, String>>()

    this.forEach { outputList.add(Pair(it.nodeName, it.nodeValue)) }

    return outputList
}
