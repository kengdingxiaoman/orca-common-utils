# orca-common-utils

orca框架基础工具类，定义许多常量和工具类

# constants包

定义各种常量

| 所属包 | 类 | 说明 |
| - | - | - |
| contants | CharConstants| 字符常量，主要是标点符号|
|  | FileConstants| 文件相关的常量|
|  | OrcaSystemConstants| orca系统通用的系统常量|
|  | RegexConstants| 正则表达式常量|
|  | WebConstants| web中会用到的常量|

# exception

只有一个类：OrcaException， orca框架异常。使用该异常，打印的日志只会显示orca框架内部的异常栈，而不会显示第三方框架的异常栈

# model

## Money

单币种货币类，处理货币算术、币种和取整。货币类中封装了货币金额和币种。目前金额在内部是long类型表示， 单位是所属币种的最小货币单位（对人民币是分）。

目前，货币实现了以下主要功能：

- 支持货币对象与double(float)/long(int)/String/BigDecimal之间相互转换。
- 货币类在运算中提供与JDK中的BigDecimal类似的运算接口， BigDecimal的运算接口支持任意指定精度的运算功能，能够支持各种可能的财务规则。
- 货币类在运算中也提供一组简单运算接口，使用这组运算接口，则在 精度处理上使用缺省的处理规则。
- 推荐使用Money，不建议直接使用BigDecimal的原因之一在于，
使用BigDecimal，同样金额和币种的货币使用BigDecimal存在多种可能的表示，例如：new BigDecimal("10.5")与 new
BigDecimal("10.50") 不相等，因为scale不等。使用Money类，同样金额和币种的货币只有一种表示方式，new
Money("10.5")和new Money("10.50")应该是相等的。
- 不推荐直接使用BigDecimal的另一原因在于，BigDecimal是Immutable，一旦创建就不可更改，对BigDecimal进行任意运算都会生成一个新的 BigDecimal对象，因此对于大批量统计的性能不够满意。Money类是mutable的，对大批量统计提供较好的支持。
- 提供基本的格式化功能。
- Money类中不包含与业务相关的统计功能和格式化功能。业务相关的功能 建议使用utility类来实现。
- Money类实现了Serializable接口，支持作为远程调用的参数和返回值。
- Money类实现了equals和hashCode方法。

# utils

定义各种工具类

## bytes

和字节相关的工具类

| 类 | 说明 |
| - | - |
| ByteArrayBuffer| 仿照 org.apache.http.util.ByteArrayBuffer 对byte的append操作进行封装，操作字节的拼接可以方便很多|
| ByteUtils| byte[]和String的各种转换|
