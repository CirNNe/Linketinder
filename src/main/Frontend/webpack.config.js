const path = require('path')
const CopyPlugin = require("copy-webpack-plugin");

module.exports = {

    mode: 'production',
    entry: [
        './src/Candidato/CadastroCandidato.ts',
        './src/Candidato/ExibeCandidato.ts',
        './src/Empresa/CadastroEmpresa.ts',
        './src/Vaga/CadastraVaga.ts',
        './src/Vaga/ExibeVaga.ts',
        './src/Grafico/GraficoCandidatos.ts',
    ],
    output: {
        filename: 'app.js',
        path: path.join(__dirname, 'dist')
    },
    resolve: {
        extensions: ['.ts', '.js']
    },
    module: {
        rules: [{
            test: /\.ts$/,
            use: 'ts-loader',
            exclude: /node_modules/
        }]
    },
    plugins: [
        new CopyPlugin({
            patterns: [
                { from: "public", to: "../dist" }
            ],
        }),
    ],
    optimization: {
        minimize: true,
    },

}