package com.dahan.carving

import org.apache.commons.cli.BasicParser
import org.apache.commons.cli.CommandLine
import org.apache.commons.cli.CommandLineParser
import org.apache.commons.cli.Options

/*
 * Creates on 2020/11/29.
 */

/**
 * 命令行解析
 * @author kevin
 */
class CarvingApplication {

    private static Options options

    private static CommandLine commandLine

    private static def COMMANDARGS = [
            ['build', 'build', 'true', '构建当前项目'],
    ]

    static {
        options = new Options()
        COMMANDARGS.eachWithIndex { List<String> entry, int i ->
            options.addOption(entry[0], entry[1], Boolean.valueOf(entry[2]), entry[3])
        }
    }

    /**
     * 解析命令
     * @param args 命令参数
     */
    static void run(String[] args) {
        CommandLineParser commandLineParser = new BasicParser()
        commandLine = commandLineParser.parse(options, args)
    }

    static void exit() {
        System.exit(0)
    }

}
