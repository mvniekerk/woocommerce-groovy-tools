import spock.lang.Specification
import za.co.koperfontein.woocommerce.tools.DbStringToMap

class DbStringToMapSpec extends Specification {
    def "Empty array"() {
        setup:
        def s = "a:0:{};"
        expect:
        DbStringToMap.dbStringToMap(s) == [:]
    }

    def "Object with one number field"() {
        setup:
        def s = 'a:1:{s:5:"value";i:400;}'
        expect:
        DbStringToMap.dbStringToMap(s) == [value: 400]
    }

    def "Object with one string field"() {
        setup:
        def s = 'a:1:{s:5:"value";s:3:"300";}'
        expect:
        DbStringToMap.dbStringToMap(s) == [value: "300"]
    }

    def "Object with one null field"() {
        setup:
        def s = 'a:1:{s:5:"value";N}'
        expect:
        DbStringToMap.dbStringToMap(s) == [value: null]
    }

    def "Nested empty object"() {
        setup:
        def s = 'a:1:{s:5:"value";a:0:{};}'
        expect:
        DbStringToMap.dbStringToMap(s) == [value: [:]]
    }

    def "Strings and nested strings"() {
        def s = 'a:2:{s:5:"value";a:1:{s:5:"total";s:5:"value";};s:5:"field":s:4:"door";}'
        expect:
        DbStringToMap.dbStringToMap(s) == [value: [total: "value"], field: "door"]
    }

    def "Numbers, strings, nested"() {
        def s = 'a:2:{s:5:"value";a:1:{s:5:"total";i:300.001;};s:5:"field":s:4:"door";}'
        expect:
        DbStringToMap.dbStringToMap(s) == [value: [total: 300.001], field: "door"]
    }

    def "Object with embedded object inside a string"() {
        def s  = 'a:2:{s:5:"value";s:474:"a:14:{s:8:"postcode";s:4:"7405";s:4:"city";s:9:"Pinelands";s:9:"address_1";s:15:"18 Hawthorn Way";s:9:"address_2";s:0:"";s:5:"state";s:2:"WC";s:7:"country";s:2:"ZA";s:17:"shipping_postcode";s:4:"7925";s:13:"shipping_city";s:9:"Woodstock";s:18:"shipping_address_1";s:29:"1st Floor Upper Eastside Bldg";s:18:"shipping_address_2";s:18:"32 Brickfield road";s:14:"shipping_state";s:2:"WC";s:16:"shipping_country";s:2:"ZA";s:13:"is_vat_exempt";b:0;s:19:"calculated_shipping";b:1;}";}'
        expect:
        DbStringToMap.dbStringToMap(s) == [value: [postcode:'7405', city:'Pinelands', address_1:'18 Hawthorn Way', address_2:'', state:'WC', country:'ZA', shipping_postcode:'7925', shipping_city:'Woodstock', shipping_address_1:'1st Floor Upper Eastside Bldg', shipping_address_2:'32 Brickfield road', shipping_state:'WC', shipping_country:'ZA', is_vat_exempt:false, calculated_shipping:true]]
    }
}
