
(function($,Edge,compId){var Composition=Edge.Composition,Symbol=Edge.Symbol;
//Edge symbol: 'stage'
(function(symbolName){Symbol.bindElementAction(compId,symbolName,"${_ball_anim}","touchend",function(sym,e){sym.getSymbol("ball_anim").play();});
//Edge binding end
Symbol.bindElementAction(compId,symbolName,"${_ball_anim}","mouseup",function(sym,e){sym.getSymbol("ball_anim").play();});
//Edge binding end
})("stage");
//Edge symbol end:'stage'

//=========================================================

//Edge symbol: 'bg_anim'
(function(symbolName){Symbol.bindTriggerAction(compId,symbolName,"Default Timeline",1000,function(sym,e){sym.play("background");});
//Edge binding end
})("bg_anim");
//Edge symbol end:'bg_anim'

//=========================================================

//Edge symbol: 'ball_anim'
(function(symbolName){Symbol.bindTriggerAction(compId,symbolName,"Default Timeline",0,function(sym,e){sym.stop();});
//Edge binding end
Symbol.bindTriggerAction(compId,symbolName,"Default Timeline",1500,function(sym,e){sym.play("start");});
//Edge binding end
})("ball_anim");
//Edge symbol end:'ball_anim'
})(jQuery,AdobeEdge,"EDGE-56876743");