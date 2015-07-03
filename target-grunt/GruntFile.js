module.exports = function (grunt) {

    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-clean');
    grunt.loadNpmTasks('grunt-contrib-copy');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-maven');

    grunt.registerTask('default', ['build']);
    grunt.registerTask('build', ['clean', 'mavenPrepare','concat', 'copy:templates', 'mavenDist']);

    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
        distdir: 'dist',
        src: {
            js: ['js/**/*.js'],
            tpl: {
                app: ['**/*.tpl.html']
            }
        },
        clean: ['<%= distdir %>/*'],
        copy: {
            templates: {
                files: [{dest: '<%= distdir %>/partials/app', expand: true, src: '<%= src.tpl.app %>', cwd: 'js/apps/'}]
            }
        },
        concat: {
            dist: {
                src: ['<%= src.js %>'],
                dest: 'dist/js/<%= pkg.name %>.js'
            },
            angular: {
                src: ['vendor/angularJS/angular.js', 'vendor/angularJS/angular-resource.js', 'vendor/angularJS/angular-route.js'],
                dest: '<%= distdir %>/vendor/angular.js'
            }
        },
        mavenPrepare: {
            options: {
                resources: ['**']
            },
            prepare: {}
        },
        mavenDist: {
            options: {
                warName: 'angularJavaee',
                deliverables: ['js'],
                gruntDistDir: 'dist'
            },
            dist: {}
        },
    });

};
