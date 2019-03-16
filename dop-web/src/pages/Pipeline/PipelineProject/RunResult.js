/**
 *  流水线信息展示，修改
 *  @author zhangfuli
 *
 * */

import React, {Component} from 'react';
import Axios from 'axios'
import API from '../../API'
import RunStep from '../components/RunStep'

export default class RunResult extends Component {
    constructor(props) {
        super(props);
        this.state = {
            stages: [],
            currentStage: 0
        }
    }
    componentWillReceiveProps (nextProps){
        let self = this;
        if(nextProps.runs){
            this.stage(nextProps.runs._links.self.href).then((stages)=>{
                console.log(stages);
                self.step(stages[self.state.currentStage])
            })
        }
    }
    componentDidUpdate (prevProps, prevState){
        if(prevState.currentStage !== this.state.currentStage){
            console.log(this.state.currentStage)
        }
    }
    //当前stage
    current(data) {
        this.setState({
            currentStage: data
        });
    }
    //获取stage
    stage(href){
        let url = 'http://jenkins.dop.clsaa.com/blue/rest/organizations/jenkins/pipelines/simple-node-app/runs/47/nodes/';
        // let url = API.jenkins + href + "nodes/";
        let self = this;
        let stages = [];
        return new Promise((resolve, reject)=>{
            Axios({
                method: 'get',
                url: url,
                headers:{
                    'Authorization': self.props.authorization
                }
            }).then((response)=>{
                if(response.status === 200){
                    response.data.map((item, index)=>{
                        let stage = {
                            id: item.id,
                            title: item.displayName,
                            time: item.durationInMillis,
                            result: item.result,
                            disabled: item.result === 'NOT_BUILT',
                            href: item._links.steps.href,
                            steps: []
                        };
                        stages.push(stage)
                    });
                    self.setState({
                        stages
                    });
                    resolve(self.state.stages)
                }
                reject()
            })
        })
    }

    //获取step
    step(stage){
        let url = API.jenkins + stage.href;
        let self = this;
        let steps = [];
        Axios({
            method: 'get',
            url: url,
            headers:{
                'Authorization': self.props.authorization
            }
        }).then((response)=>{
            console.log(response);
            if(response.status === 200){
                response.data.map((item, index)=>{
                    let step = {
                        id: item.id,
                        displayDescription: item.displayDescription,
                        displayName: item.displayName,
                        result: item.result,
                        time: item.durationInMillis,
                        logHref: item.actions.length === 0 ? null:item.actions[0]._links.self.href,
                        log: []
                    };
                    steps.push(step);
                });
                stage.steps = steps;
                console.log(stage)
            }
        })
    }
    render() {
        return (
            <div>
                <RunStep stages={this.state.stages} currentStage={this.state.currentStage} current={this.current.bind(this)}/>


            </div>
        );
    }
}
