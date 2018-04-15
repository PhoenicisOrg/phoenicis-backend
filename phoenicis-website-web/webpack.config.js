const path = require('path');
const prod = process.argv.indexOf('-p') !== -1;

config = {
    context: __dirname,
    entry: {
        frontend: "./src/main/frontend/org/phoenicis/website/web/index.js"
    },
    output: {
        path: path.resolve(__dirname, "src/main/resources/org/phoenicis/website/web/static"),
        filename: "[name].bundle.js"
    },
    resolve: {
        extensions: ['.ts', '.tsx', '.js']
    },
    module: {
        loaders: [ // loaders will work with webpack 1 or 2; but will be renamed "rules" in future
            // all files with a `.ts` or `.tsx` extension will be handled by `ts-loader`
            {
                test: /\.tsx?$/, loader: 'ts-loader'
            },
            {
                test: /\.(scss|sass)$/,
                use: [{
                    loader: "style-loader" // creates style nodes from JS strings
                }, {
                    loader: "css-loader" // translates CSS into CommonJS
                }, {
                    loader: "sass-loader" // compiles Sass to CSS
                }]
            },
            {
                test: /\.css$/,
                use: ['style-loader', 'css-loader']
            },
            {
                test: /\.woff(2)?(\?v=[0-9]\.[0-9]\.[0-9])?$/,
                loader: "url-loader?limit=10000&mimetype=application/font-woff"
            },
            {
                test: /\.(ttf|eot|svg)(\?v=[0-9]\.[0-9]\.[0-9])?$/,
                loader: "file-loader"
            },
            {
                test: /\.(html|png|jpg)|phoenicis\.svg$/,
                loader: "file-loader",
                options: {
                    context: path.resolve(__dirname, "src/main/frontend/org/phoenicis/website/web"),
                    name: "[path][name].[ext]"
                }
            }
        ]
    }
};

if(!prod) {
    config.devtool = "source-map";
}

module.exports = config;
