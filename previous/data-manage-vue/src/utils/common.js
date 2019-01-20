export function pad(num, n) {
  let len = num.toString().length;
  while(len < n) {
    num = "0" + num;
    len++;
  }
  return num;
}

'use strict';
/**
 *燃料定义
 * @type {any[]}
 */
export const fuelArray=[
  Object.defineProperties({},{value:{value:0,configurable:false},label:{value:'油气',configurable:false}}),
  Object.defineProperties({},{value:{value:1,configurable:false},label:{value:'电',configurable:false}}),
  Object.defineProperties({},{value:{value:2,configurable:false},label:{value:'煤',configurable:false}}),
  Object.defineProperties({},{value:{value:3,configurable:false},label:{value:'生物质',configurable:false}}),
]
/**
 * 介质定义
 * @type {any[]}
 */
export const mediumArray=[
  Object.defineProperties({},{value:{value:0,configurable:false},label:{value:'热水',configurable:false}}),
  Object.defineProperties({},{value:{value:1,configurable:false},label:{value:'蒸汽',configurable:false}}),
  Object.defineProperties({},{value:{value:2,configurable:false},label:{value:'导热油',configurable:false}}),
  Object.defineProperties({},{value:{value:3,configurable:false},label:{value:'热风',configurable:false}}),
  Object.defineProperties({},{value:{value:4,configurable:false},label:{value:'真空',configurable:false}}),
]


