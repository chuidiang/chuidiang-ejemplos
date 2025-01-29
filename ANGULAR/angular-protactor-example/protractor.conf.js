exports.config = {
    framework: 'jasmine',
    seleniumAddress: 'http://localhost:4444/wd/hub',
    specs: ['src/**/*.e2e-spec.ts'],
    capabilities: {
      browserName: 'chrome'
    },
    baseUrl: 'http://localhost:4200/', // URL de la aplicación en desarrollo
    jasmineNodeOpts: {
      showColors: true,
      defaultTimeoutInterval: 30000
    }
  };