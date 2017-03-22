package za.co.koperfontein.woocommerce.tools

import groovy.json.JsonBuilder

class Main {
    static main (args) {
        println "Hi"
        def s = """
a:7:{s:21:"removed_cart_contents";s:6:"a:0:{}";s:10:"wc_notices";N;s:22:"shipping_for_package_0";s:480:"a:2:{s:12:"package_hash";s:40:"wc_ship_4547e12b9a5a0fd08bdd5e6bd50259a9";s:5:"rates";a:1:{s:19:"legacy_local_pickup";O:16:"WC_Shipping_Rate":6:{s:2:"id";s:19:"legacy_local_pickup";s:5:"label";s:12:"Local Pickup";s:4:"cost";s:4:"0.00";s:5:"taxes";a:0:{}s:9:"method_id";s:19:"legacy_local_pickup";s:27:" WC_Shipping_Rate meta_data";a:1:{s:5:"Items";s:120:"Poecilotheria ornata - Fringed ornamental &times; 2, Grammostola rosea- Chilean rose &times; 1, New water dish &times; 3";}}}}";s:23:"chosen_shipping_methods";s:37:"a:1:{i:0;s:19:"legacy_local_pickup";}";s:22:"shipping_method_counts";s:14:"a:1:{i:0;i:1;}";s:21:"chosen_payment_method";s:4:"bacs";s:8:"customer";s:474:"a:14:{s:8:"postcode";s:4:"7405";s:4:"city";s:9:"Pinelands";s:9:"address_1";s:15:"18 Hawthorn Way";s:9:"address_2";s:0:"";s:5:"state";s:2:"WC";s:7:"country";s:2:"ZA";s:17:"shipping_postcode";s:4:"7925";s:13:"shipping_city";s:9:"Woodstock";s:18:"shipping_address_1";s:29:"1st Floor Upper Eastside Bldg";s:18:"shipping_address_2";s:18:"32 Brickfield road";s:14:"shipping_state";s:2:"WC";s:16:"shipping_country";s:2:"ZA";s:13:"is_vat_exempt";b:0;s:19:"calculated_shipping";b:1;}";}
""".trim()
        println "S: ${new JsonBuilder(DbStringToMap.dbStringToMap(s)).toPrettyString()}"
    }
}
