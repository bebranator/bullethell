#version 330 core

varying vec2 v_texCoords;
varying vec4 v_color;
varying vec4 v_mix_color;

uniform float u_progress; // from 0 to 1
uniform sampler2D u_texture, u_texture2; // u_texture2 for loading stuff
uniform vec2 u_resolution;

void main( )
{
//    gl_FragColor = vec4(0);
    float depth = -1e3;
    for (float i=0. ; i<=1. ; ++i)
    {
        vec2 xy = v_texCoords / 2.0;
        float grid_width = 64.0;
        xy /= grid_width;
        xy.y += i + .5;
        xy.y /= 2.;
        vec2 grid = floor(xy);
        xy -= grid + 0.5;
        xy.y *= 2.;
        grid.y = grid.y * 2. - i;

        float phase = 0.0;//iMouse.x / iResolution.x;
        float offset = (grid.y - grid.x)*0.1;
        float time = u_progress*1.0 - offset;
        // looping + wrapping
        time = mod(time, 6.0);
        phase += smoothstep(0.0, 1.0, time);
        phase += 1.0 - smoothstep(3.0, 4.0, time);
        phase = abs(mod(phase, 2.0)-1.0);

        float side = step(0.5, phase);

        float angle = radians(phase * 180.), z = 2.;
        vec3 p = inverse(mat3(cos(angle),0,-sin(angle), 0,1,0, 0,0,z)) * vec3(xy, z);
        vec2 uv = p.xy / p.z + .5;

        float alpha = 1.;
        if (uv.x>=0.0&&uv.y>=0.0&&uv.x<=1.0&&uv.y<=1.0 && p.z>depth)
            depth = p.z;
        else
            alpha = 0.;

//        vec2 scale = grid_width / u_resolution.xy;
        vec2 scale = vec2(grid_width / u_resolution.x, grid_width / u_resolution.y);
        vec2 uv1 = (grid + uv                   ) * scale + .5;
        vec2 uv2 = (grid + vec2(1. - uv.x, uv.y)) * scale + .5;
        vec4 c1 = texture(u_texture, uv1);
        vec4 c2 = texture(u_texture2, uv2);

        gl_FragColor = mix(gl_FragColor, mix(c1, c2, side), alpha);
        //        fragColor = mix(fragColor, vec4(p.z-1., 1.-p.z, 0, 1)*10., alpha);
    }
}
