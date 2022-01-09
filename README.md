# Worldline Tech Assesment

Derived from the assessment given. Assuming its a real project with software, infrastructure listed as requirement

## Software Architecture
### Vision/project goal statement
```
	FOR         World Line
	WHO         want to add WOOD and COAL fuel support 
				for their Widget Machine
	THE         Stream Engine is a microservice
	THAT        let customer calculate production cost by using WOOD and COAL fuel
	UNLIKE      other Widget Machine
	OUR PRODUCT lets customer get production cost in quick and simple way  
```
### Problem & Solution space definition
Problem and solution are mapped like follow
![alt text](https://github.com/tommyhutomo/worldline-tech-assessment/blob/master/image/SolutionSpace.jpeg?raw=true)

### Implementation space definition

![alt text](https://github.com/tommyhutomo/worldline-tech-assessment/blob/master/image/stages.jpeg?raw=true)

Implementation space split 2 version as follow:
*	[Arch Transition 1 - v1](https://github.com/tommyhutomo/worldline-tech-assessment/tree/master/v.1)
*	[Arch Transition 2 - v2](https://github.com/tommyhutomo/worldline-tech-assessment/tree/master/v.2)

## Infra Architecture
### Virtualization 
Assuming using Baremetal, vsphere, Red Hat Openshift. The virtualization will be like:

![alt text](https://github.com/tommyhutomo/worldline-tech-assessment/blob/master/image/layout.jpeg?raw=true)
 
### k8s project/namespace structure
Assuming using Red Hat Openshift, project/namespace will be like:

![alt text](https://github.com/tommyhutomo/worldline-tech-assessment/blob/master/image/k8sNamespace.jpeg?raw=true)