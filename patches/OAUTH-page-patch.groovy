// Use inputs to attach smartthings devices to this app
preferences {
   page(name: "connectDevPage")
   page(name: "oauthPage")
   }

def connectDevPage() {
   dynamicPage(name: "connectDevPage", title:"Connect Devices", nextPage: "oauthPage",  uninstall: true ) {
      section(title: "Select Devices") {
        input "brlight", "capability.switch", title: "Select the Bedroom Light", required: true, multiple:false
        //  ALL YOUR INPUTS go here

      }
   }
}

def oauthPage() {
   if (!state.tok) {          
      try {
         state.tok = createAccessToken()
      } catch (error) {
         paragraph "Enable OAuth in the SmartApp Editor App Settings"
         state.tok = ""
      }
   }
   dynamicPage(name: "oauthPage", title:"",  uninstall: false ) {
      section(title: "Show the OAUTH ID/Token Pair") {
        paragraph "   var STappID = '${app.id}';\n   var STtoken = '${state.tok}';\n"
      }
   }
      
}

