package cn.xstar.site.util;

import org.hibernate.dialect.MySQL57Dialect;

/**
 * 建表时添加默认 设置为utf8mb4 支持4bit unicode值
 */
public class Mysql5DialectUtf8 extends MySQL57Dialect {
    @Override
    public String getTableTypeString() {
        return String.format(" %s=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE utf8mb4_unicode_ci", getEngineKeyword());
    }

}
