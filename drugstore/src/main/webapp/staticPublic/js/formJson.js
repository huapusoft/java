jQuery.formHelper = {
    getObject: function (urlserialize) {
        if (urlserialize) {
            urlserialize = decodeURI(urlserialize);
            var kvs = $.map((urlserialize).split('&'), function (e, i) {
                var kv = (e + '').split('=');
                return { key: kv[0], value: kv[1] };
            });
            var params = {};
            for (var key in kvs) {
                var _key = kvs[key].key;
                var value = null;
                if (typeof (params[_key]) == 'undefined') {
                    if ($.grep(kvs, function (e, i) { return e.key == _key; }).length > 1) {
                        value = [];
                        value.push(kvs[key].value);
                        params[_key] = value;
                    } else {
                        value = kvs[key].value;
                        params[_key] = value;
                    }
                } else if (typeof (params[_key]) == 'object') {
                    value = params[_key] ? params[_key] : [];
                    value.push(kvs[key].value);
                    params[_key] = value;
                } else {
                    value = kvs[key].value;
                    params[_key] = value;
                }
            }
            var resultParams = {};
            var objectParams = [];
            var indexs = {};
            for (var key in params) {
                var lstKey = key.substr(key.length - 6, 6);
                var subKey = key.substr(0, key.length - 6);
                var isIndex = (lstKey == '.index' && (urlserialize.indexOf(subKey + '[') == 0 || urlserialize.indexOf('&' + subKey + '[') > 0));
                var indexArr = key.match(/\[([^\]]+)\]/ig);
                if (indexArr) {
                    var __key = key;
                    for (var i = 0; i < indexArr.length; i++) {
                        var _index = (indexArr[i] + '');//.replace(/[\[\]]/g, '');
                        var indexof = __key.indexOf(indexArr[i]);
                        if (typeof (indexs[__key.substr(0, indexof)]) == 'undefined') {
                            indexs[__key.substr(0, indexof)] = [];
                        }
                        if (indexs[__key.substr(0, indexof)].indexOf(_index) < 0) {
                            indexs[__key.substr(0, indexof)].push(_index);
                        }
                        __key = __key.replace('[', '_').replace(']', '_');
 
                    }
 
                    //console.log(__key);
                    var keys = key.split('.');
                    var path = '';
                    var _path = '';
                    var __path = '';
                    for (var ik in keys) {
                        var ikey = keys[ik];
                        if (path == '') {
                            path = keys[ik];
                        } else {
                            path = path + '.' + keys[ik];
                        }
                        var pathArr = path.match(/\[([^\]]+)\]/ig);
                        if (__path == '') {
                            __path = path;
                        } else {
                            __path = __path + '.' + ikey;
                        }
                        if (_path == '') {
                            _path = path;
                        } else {
                            _path = _path + '.' + ikey;
                        }
                        //_path = path;
                        for (var ip in pathArr) {
                            var ipath = pathArr[ip];
                            var indexof = __path.indexOf(ipath);
                            var _index = __path.substr(0, indexof).replace('[', '_').replace(']', '_');
                            if (indexof > -1) {
                                _path = _path.replace(ipath, '[' + indexs[_index].indexOf(ipath) + ']');
                                _path = _path.replace('[', '{').replace(']', '}');
                            }
                            if (_path.indexOf('[') < 0) {
                                if (!isIndex) {
                                    //初始化定义开始
                                    var evelCode = 'resultParams.' + _path.replace(/\{/g, '[').replace(/\}/g, ']');
                                    if (evelCode.substr(evelCode.length - 1, 1) == ']') {
                                        if (typeof (eval(evelCode.substr(0, evelCode.lastIndexOf('[')))) == 'undefined') {
                                            eval(evelCode.substr(0, evelCode.lastIndexOf('[')) + '=[];');
                                        } else {
                                            //console.log("hv", eval(evelCode.substr(0, evelCode.lastIndexOf('['))));
                                        }
                                    }
                                    try {
                                        if (typeof (eval(evelCode)) == 'undefined') {
                                            eval(evelCode + '={};');
                                        }
                                    } catch (e) {
                                        //console.log('error', evelCode);
                                    }
                                    //定义结束
                                    //赋值
                                    if (typeof (params[key]) != 'undefined') {
                                        var keyArrays = key.split('.');
                                        var codeArrays = evelCode.split('.');
                                        if (keyArrays[keyArrays.length - 1] == codeArrays[codeArrays.length - 1]) {
                                            try {
                                                eval(evelCode + '=params[key];');
                                            } catch (e) {
                                                //console.log(path);
                                            }
                                        }
                                    }
                                }
 
                            }
                        }
                        __path = __path.replace('[', '_').replace(']', '_');
                    }
                } else {
                    if (!isIndex) {
                        var keys = key.split('.');
                        var path = 'resultParams';
                        for (var ik in keys) {
                            path = path + '.' + keys[ik];
                            try {
                                if (typeof (eval(path)) == 'undefined') {
                                    eval(path + '={};');
                                }
                            } catch (e) {
                                //忽略不规则的
                            }
                        }
                        try {
                            eval(path + '=params[key];');
                        } catch (e) {
                            //忽略不规则的
                        }
                    }
                }
            }
            return resultParams;
        }
        return null;
    }
};
 
/*
 *使用范例
 * <form id="post-form">
 * <label>姓名</label><input name="name" type="text" /><br/>
 * <label>性别</label><input name="sex" type="text" /><br/>
 * <b>联系方式：</b><label>手机</label><input name="contact.phone" type="text" /><label>邮箱</label><input name="contact.email" type="text" />
 * <b>成绩：</b><br />
 * <ol>
 * <li>
 * <p>语文<input name="score.index" type="hidden" value="s_1" /><input name="score[s_1].title" type="hidden" value="语文" /></p>
 * <p><input name="score[s_1].value" type="text" /></p>
 * </li>
 * <li>
 * <p>数学<input name="score.index" type="hidden" value="s_2" /><input name="score[s_2].title" type="hidden" value="语文" /></p>
 * <p><input name="score[s_2].value" type="text" /></p>
 * </li>
 * <li>
 * <p>其他成绩<input name="score.index" type="hidden" value="s_3" /><input name="score[s_3].title" type="hidden" value="其他成绩" /></p>
 * <p>
 * <ul>
 *      <li>
 *          <p>德<input name="score[s_3].value.index" type="hidden" value="s3_1" /><input name="score[s_3].value[s3_1].title" type="hidden" value="其他成绩" /></p>
 *          <p>
 *              <label><input name="score[s_3].value[s3_1].value" type="radio" value="差" />差</label>
 *              <label><input name="score[s_3].value[s3_1].value" type="radio" value="一般" />一般</label>
 *              <label><input name="score[s_3].value[s3_1].value" type="radio" value="很好" />很好</label>
 *          </p>
 *      </li>
 *      <li>
 *          <p>智<input name="score[s_3].value.index" type="hidden" value="s3_2" /><input name="score[s_3].value[s3_2].title" type="hidden" value="智" /></p>
 *          <p>
 *              <label><input name="score[s_3].value[s3_2].value" type="radio" value="差" />差</label>
 *              <label><input name="score[s_3].value[s3_2].value" type="radio" value="一般" />一般</label>
 *              <label><input name="score[s_3].value[s3_2].value" type="radio" value="很好" />很好</label>
 *          </p>
 *      </li>
 *      <li>
 *          <p>体育特长<input name="score[s_3].value.index" type="hidden" value="s3_3" /><input name="score[s_3].value[s3_3].title" type="hidden" value="体育特长" /></p>
 *          <p>
 *              <label><input name="score[s_3].value[s3_3].value" type="checkbox" value="跑步" />跑步</label>
 *              <label><input name="score[s_3].value[s3_3].value" type="checkbox" value="跳高" />跳高</label>
 *              <label><input name="score[s_3].value[s3_3].value" type="checkbox" value="足球" />足球</label>
 *              <label><input name="score[s_3].value[s3_3].value" type="checkbox" value="篮球" />篮球</label>
 *              <label><input name="score[s_3].value[s3_3].value" type="checkbox" value="其他" />其他</label>
 *          </p>
 *      </li>
 * </ul>
 * </p>
 * </li>
 * </ol>
 * </form> 
 * <script>
 * $(function(){
 *      console.log($.formHelper.getObject($('#post-form').serialize()));
 * //得到的结构是：
 * {
 *      name:'',
 *      sex:'',
 *      contact:{
 *          phone:'',
 *          email:''
 *      }
 *      score:[
 *          {
 *              title:'语文',value:''
 *          }
 *          {
 *              title:'数学',value:''
 *          }
 *          {
 *              title:'其他成绩'
 *              value:[
 *                  {title:'德',value:''}
 *                  {title:'智',value:''}
 *                  {title:'体育特长',value:[]}
 *              ]
 *          }
 *      ]
 * }
 * 
 * 
 * });
 * </script>
 */