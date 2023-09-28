docker_build('pravinsm94/springboot-async', '.')
k8s_yaml('/deployment/kubernetes.yaml')
k8s_resource(workload='springboot-async', port_forwards=9095, links=[link('http://localhost:9095/swagger-ui/index.html','Swagger-UI')])
