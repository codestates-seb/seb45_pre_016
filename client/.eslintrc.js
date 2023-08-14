export const env = {
  parser: "@babel/eslint-parser",
  node: true,
  extends: [
    'plugin:prettier/recommended',
    'eslint:recommended',
    'plugin:react/recommended',
  ],
  overrides: [
    {
      env: {
        node: true,
        browser: true,
      },
      files: ['.eslintrc.js'],
      parserOptions: {
        sourceType: 'script',
        ecmaVersion: 'latest',
        sourceType: 'module',
      },
    },
  ],
  plugins: ['react', 'prettier'],
  rules: {
    'prettier/prettier': [
      'error',
      { singleQuote: true, semi: false, endOfLine: 'auto', useTabs: false },
    ],
    'space-before-function-paren': ['error', 'never'],
    'arrow-body-style': 'off',
    'prefer-arrow-callback': 'off',
  },
}
