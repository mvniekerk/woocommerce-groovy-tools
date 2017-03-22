package za.co.koperfontein.woocommerce.tools

import groovy.transform.CompileStatic

class DbStringToMap {

    @CompileStatic
    static String readInValues(String s, Map<String, Object> val) {
        while (s && s[0] != "}") {
            def b = s.startsWith("s:")
            s = s.substring(2)
            if (!b) {
                continue    //Not handling arrays yet
            }
            def sLen = s.substring(0, s.indexOf(":")).toInteger()
            s = s.substring("$sLen".length() + 2)
            def key = s.substring(0, sLen)
            s = s.substring(sLen + 2)

            def valType = s[0]

            switch (valType) {
                case "a":
                    //Object
                    s = s.substring(2)
                    def v = [:]
                    sLen = s.substring(0, s.indexOf(":")).toInteger()
                    s = s.substring("$sLen".length() + 2)
                    s = readInValues s, v
                    s = s.substring(1)
                    val[key] = v
                    break
                case "b":
                    //Boolean
                    s = s.substring(2)
                    val[key] = s.substring(0, 1) == "1"
                    s = s.substring(2)
                    break
                case "s":
                    //String
                    s = s.substring(2)
                    sLen = s.substring(0, s.indexOf(":")).toInteger()
                    s = s.substring("$sLen".length() + 2)
                    def v = s.substring(0, sLen)
                    s = s.substring(sLen + 2)
                    if (v.startsWith("a:")) {
                        def vv = [:]
                        println "V: $v"
                        v = v.substring(2)
                        sLen = v.substring(0, v.indexOf(":")).toInteger()
                        v = v.substring("$sLen".length()+2)
                        readInValues v, vv
                        val[key] = vv
                        println "VV[$key]: ${vv}"
                    } else {
                        val[key] = v
                    }
                    break
                case "d":
                case "i":
                    //Integer
                    s = s.substring(2)
                    def v = s.substring(0, s.indexOf(";")).toBigDecimal()
                    s = s.substring("$v".length() + 1)
                    val[key] = v
                    break
                case "N":
                    //Null
                    val[key] = null
                    s = s.substring(2)
                    break
            }
        }
        s && s[0] == "}" ? s.substring(1) : s
    }

    static Map<String, Object> dbStringToMap(String s) {
        def ret = [:]

        assert s.startsWith("a:")
        s = s.substring(2)
        def size = s.substring(0, s.indexOf(":")).toInteger()
        if (size) {
            readInValues s.substring("$size".length() + 2), ret
        }
        ret
    }
}
